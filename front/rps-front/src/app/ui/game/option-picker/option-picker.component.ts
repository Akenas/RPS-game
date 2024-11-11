import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-option-picker',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './option-picker.component.html',
  styleUrl: './option-picker.component.css'
})
export class OptionPickerComponent {

  @Input() availableOptions : string[] = []
  @Output() optionLocked = new EventEmitter<number | null>();
  selectedOptionIndex: number | null = null;

  selectOption(index: number): void {
    this.selectedOptionIndex = index;
    
  }

  lockSelection(){
    if (this.selectedOptionIndex !== null) {
      this.optionLocked.emit(this.selectedOptionIndex);
    }
  }
}
