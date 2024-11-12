import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverlayMenuComponent } from './overlay-menu.component';

describe('HomeButtonComponent', () => {
  let component: OverlayMenuComponent;
  let fixture: ComponentFixture<OverlayMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OverlayMenuComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OverlayMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
