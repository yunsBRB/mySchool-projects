
import { Component, input } from '@angular/core';

@Component({
  selector: 'app-confirm',
  imports: [],
  templateUrl: './confirm.html',
  styleUrl: './confirm.scss'
})
export class Confirm {
  productName = input<string>('');
}
