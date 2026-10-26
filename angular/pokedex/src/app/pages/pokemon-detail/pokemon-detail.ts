import { Component, computed, effect, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { map } from 'rxjs';

import { PokemonService } from '../../services/pokemon.service';
import { TypeBadges } from '../../components/type-badges/type-badges';
import { artwork, numberLabel } from '../../utils/pokemon.utils';

@Component({
  selector: 'app-pokemon-detail',
  imports: [RouterLink, TypeBadges],
  templateUrl: './pokemon-detail.html',
  styleUrl: './pokemon-detail.scss'
})
export class PokemonDetail {
  readonly data = inject(PokemonService);
  private readonly route = inject(ActivatedRoute);

  readonly id = toSignal(
    this.route.paramMap.pipe(
      map(params => Number(params.get('id')))
    ),
    { initialValue: 0 }
  );

  readonly pokemon = computed(() =>
    this.data.catalog().find(p => p.id === this.id())
  );

  readonly description = signal('');
  readonly artwork = artwork;
  readonly numberLabel = numberLabel;

  private requestId = 0;

  constructor() {
    void this.data.loadCatalog();

    effect(() => {
      const p = this.pokemon();
      if (p) void this.loadDescription(p.id);
    });
  }

  async loadDescription(id: number): Promise<void> {
    const request = ++this.requestId;
    this.description.set('');

    try {
      const species = await this.data.species(id);

      if (request !== this.requestId) return;

      const entry =
        species.flavor_text_entries.find(e => e.language.name === 'fr') ??
        species.flavor_text_entries.find(e => e.language.name === 'en');

      this.description.set(
        entry?.flavor_text.replace(/[\n\f]/g, ' ') ?? ''
      );
    } catch {
      if (request === this.requestId) {
        this.description.set('');
      }
    }
  }
}
