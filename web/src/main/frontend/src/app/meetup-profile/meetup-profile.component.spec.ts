import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetupProfileComponent } from './meetup-profile.component';

describe('MeetupProfileComponent', () => {
  let component: MeetupProfileComponent;
  let fixture: ComponentFixture<MeetupProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeetupProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeetupProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
