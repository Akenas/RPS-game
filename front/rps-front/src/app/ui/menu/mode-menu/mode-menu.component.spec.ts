import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeMenuComponent } from './mode-menu.component';

describe('ModeMenuComponent', () => {
  let component: ModeMenuComponent;
  let fixture: ComponentFixture<ModeMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModeMenuComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModeMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
