import { Component, computed, inject, signal } from '@angular/core';
import { PokemonService } from '../../services/pokemon.service';
import { PokemonCard } from '../../components/pokemon-card/pokemon-card';
import { normalize } from '../../utils/pokemon.utils';

@Component({
  selector: 'app-home',
  imports: [PokemonCard],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home {
  readonly data = inject(PokemonService);
  readonly search = signal('');

  readonly pokemon = computed(() => {
    const query = normalize(this.search()).replace(/^#/, '');

    if (!query) return this.data.catalog();

    return this.data.catalog().filter(p =>
      normalize(p.name).includes(query) ||
      normalize(p.en).includes(query) ||
      String(p.id) === query
    );
  });

  constructor() {
    void this.data.loadCatalog();
  }
}
