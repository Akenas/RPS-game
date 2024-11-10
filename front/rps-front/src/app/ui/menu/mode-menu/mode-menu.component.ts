import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ModeOption } from '../../../model/mode-option.model';

@Component({
  selector: 'app-mode-menu',
  standalone: true,
  imports: [ConfirmDialogModule, CommonModule],
  templateUrl: './mode-menu.component.html',
  styleUrl: './mode-menu.component.css'
})
export class ModeMenuComponent {

  @Input() menuOptions: ModeOption[] = [];
  @Output() modeSelected = new EventEmitter<ModeOption>();
  
  selectedMode: ModeOption | null = null;

  selectMode(mode: ModeOption) {
    this.selectedMode = mode;
  }

  queueMode() {
    if (this.selectedMode) {
      this.modeSelected.emit(this.selectedMode);
    }
  }
}
