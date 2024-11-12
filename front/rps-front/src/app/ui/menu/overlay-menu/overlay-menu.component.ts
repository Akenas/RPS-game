import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-overlay-menu',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './overlay-menu.component.html',
  styleUrl: './overlay-menu.component.css'
})
export class OverlayMenuComponent {

  @Input() onClick: () => void = () => {};
  @Input() icon: string = "home";
  @Input() options: string[] = []; // Parent component will provide the options to display
  @Input() closeable: boolean = true;

  isOverlayVisible: boolean = false;

  openOverlay() {
    this.isOverlayVisible = true;
  }

  closeOverlay() {
    this.isOverlayVisible = false;
  }
}
