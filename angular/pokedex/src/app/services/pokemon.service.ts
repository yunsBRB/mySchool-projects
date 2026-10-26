import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom, timeout } from 'rxjs';
import type { Pokemon, Species, EvolutionChain, PokemonDetails } from '../models/pokemon';

@Injectable({ providedIn: 'root' })
export class PokemonService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'https://pokeapi.co/api/v2/';
  private readonly cache = new Map<string, Promise<unknown>>();
  readonly catalog = signal<Pokemon[]>([]);
  readonly loading = signal(false);
  readonly error = signal('');

  async loadCatalog(): Promise<void> {
    if (this.catalog().length || this.loading()) return;
    this.loading.set(true);
    this.error.set('');
    try {
      this.catalog.set(await firstValueFrom(this.http.get<Pokemon[]>('data/catalog.json').pipe(timeout(15000))));
    } catch { this.error.set('Catalogue indisponible.'); }
    finally { this.loading.set(false); }
  }
  species(id: number) { return this.get<Species>('pokemon-species/' + id); }
  pokemon(id: number) { return this.get<PokemonDetails>('pokemon/' + id); }
  evolution(url: string) { return this.get<EvolutionChain>(url); }

  private get<T>(path: string): Promise<T> {
    const url = path.startsWith(this.baseUrl) ? path : this.baseUrl + path;
    if (!this.cache.has(url)) {
      const request = firstValueFrom(this.http.get<T>(url).pipe(timeout(15000)))
        .catch(error => { this.cache.delete(url); throw error; });
      this.cache.set(url, request);
    }
    return this.cache.get(url) as Promise<T>;
  }
}
