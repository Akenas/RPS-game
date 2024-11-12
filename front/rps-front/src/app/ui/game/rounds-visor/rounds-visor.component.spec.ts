import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoundsVisorComponent } from './rounds-visor.component';

describe('RoundsVisorComponent', () => {
  let component: RoundsVisorComponent;
  let fixture: ComponentFixture<RoundsVisorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoundsVisorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoundsVisorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
