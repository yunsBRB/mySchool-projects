
import { Component, OnDestroy, signal } from '@angular/core';
import { DureePipe } from './duree.pipe';

@Component({
  selector: 'app-chronometre',
  imports: [DureePipe],
  templateUrl: './chronometre.html',
  styleUrl: './chronometre.scss'
})
export class Chronometre implements OnDestroy {
  secondes = signal(0);
  private timer: ReturnType<typeof setInterval> | null = null;

  start() {
    if (this.timer !== null) return;

    this.timer = setInterval(() => {
      this.secondes.update(s => s + 1);
    }, 1000);
  }

  stop() {
    if (this.timer !== null) {
      clearInterval(this.timer);
      this.timer = null;
    }
  }

  reset() {
    this.stop();
    this.secondes.set(0);
  }

  ngOnDestroy() {
    this.stop();
  }
}
