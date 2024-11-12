import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-queue-controller',
  standalone: true,
  imports: [],
  templateUrl: './queue-controller.component.html',
  styleUrl: './queue-controller.component.css'
})
export class QueueControllerComponent {

  @Output() queueCancelled = new EventEmitter<void>();

  cancelQueue(){
    this.queueCancelled.emit();
  }
}
