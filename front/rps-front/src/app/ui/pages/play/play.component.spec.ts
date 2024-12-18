import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayPageComponent } from './play.component';

describe('PlayComponent', () => {
  let component: PlayPageComponent;
  let fixture: ComponentFixture<PlayPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlayPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlayPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
