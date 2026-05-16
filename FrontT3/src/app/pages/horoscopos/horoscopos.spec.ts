import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Horoscopos } from './horoscopos';

describe('Horoscopos', () => {
  let component: Horoscopos;
  let fixture: ComponentFixture<Horoscopos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Horoscopos],
    }).compileComponents();

    fixture = TestBed.createComponent(Horoscopos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
