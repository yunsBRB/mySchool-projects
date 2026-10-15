
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'duree',
  standalone: true
})
export class DureePipe implements PipeTransform {
  transform(secondes: number): string {
    const h = Math.floor(secondes / 3600);
    const m = Math.floor((secondes % 3600) / 60);
    const s = secondes % 60;

    return [h, m, s]
      .map(n => String(n).padStart(2, '0'))
      .join(':');
  }
}
