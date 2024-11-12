import { Component, Input } from '@angular/core';
import { Round } from '../../../model/roud.model';

@Component({
  selector: 'app-pick-visor',
  standalone: true,
  imports: [],
  templateUrl: './pick-visor.component.html',
  styleUrl: './pick-visor.component.css'
})
export class PickVisorComponent {

  @Input() round : Round | undefined;
  @Input() player1 : string | undefined;
  @Input() player2 : string | undefined;

  isVisible: boolean = false;

  ngOnInit(): void {
    
  }
  
  ngOnChanges(): void {
    if (this.round) {
      this.isVisible = false;

      setTimeout(() => {
        this.isVisible = true;
      }, 50); // Short delay to allow Angular to detect changes
    }
  }
}
