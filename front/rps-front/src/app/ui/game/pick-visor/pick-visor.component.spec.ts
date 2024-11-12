import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PickVisorComponent } from './pick-visor.component';

describe('PickVisorComponent', () => {
  let component: PickVisorComponent;
  let fixture: ComponentFixture<PickVisorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PickVisorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PickVisorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
