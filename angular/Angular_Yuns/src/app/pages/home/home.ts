
import { Component } from '@angular/core';
import { Chronometre } from '../../chronometre/chronometre';

@Component({
  selector: 'app-home',
  imports: [Chronometre],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home {}
