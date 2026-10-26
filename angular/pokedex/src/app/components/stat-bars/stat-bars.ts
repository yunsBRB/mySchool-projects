import {Component, input} from '@angular/core';
import {STAT_LABELS} from '../../models/pokemon.constants';

@Component({selector: 'app-stat-bars', templateUrl: './stat-bars.html'})
export class StatBars {
  readonly stats = input.required<number[]>();
  readonly labels = STAT_LABELS;
}
