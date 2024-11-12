import { Component, Input } from '@angular/core';
import { Round } from '../../../model/roud.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-rounds-visor',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rounds-visor.component.html',
  styleUrl: './rounds-visor.component.css'
})
export class RoundsVisorComponent {

  @Input() roundsToPlay : number = 0
  @Input() rounds : Round[]  = []

  getRoundClass(winner: number|undefined) : string {
    if ( winner == -1 || winner === null) {
      return '';
    }
    switch (winner) {
      case 1:
        return 'wp1';
      case 2:
        return 'wp2';
      case 0:
      default:
        return 'tie';
    }
  }

}
