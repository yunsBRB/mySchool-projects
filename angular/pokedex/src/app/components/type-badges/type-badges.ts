import { Component, input } from '@angular/core';
import { TYPES } from '../../models/pokemon.constants';
@Component({ selector: 'app-type-badges', templateUrl: './type-badges.html' })
export class TypeBadges {
  readonly types = input.required<string[]>();
  readonly labels = TYPES;
}
