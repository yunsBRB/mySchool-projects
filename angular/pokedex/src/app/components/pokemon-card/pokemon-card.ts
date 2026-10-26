import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Pokemon } from '../../models/pokemon';
import { TYPES } from '../../models/pokemon.constants';
import { TypeBadges } from '../type-badges/type-badges';
import { artwork, numberLabel } from '../../utils/pokemon.utils';

@Component({
  selector: 'app-pokemon-card',
  imports: [RouterLink, TypeBadges],
  templateUrl: './pokemon-card.html',
  styleUrl: './pokemon-card.scss'
})
export class PokemonCard {
  readonly pokemon = input.required<Pokemon>();
  readonly artwork = artwork;
  readonly numberLabel = numberLabel;
  readonly types = TYPES;
}
