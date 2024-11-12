import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QueueControllerComponent } from './queue-controller.component';

describe('QueueControllerComponent', () => {
  let component: QueueControllerComponent;
  let fixture: ComponentFixture<QueueControllerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QueueControllerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QueueControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
