import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpeakerProfileToUsersComponent } from './speaker-profile-to-users.component';

describe('SpeakerProfileToUsersComponent', () => {
  let component: SpeakerProfileToUsersComponent;
  let fixture: ComponentFixture<SpeakerProfileToUsersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpeakerProfileToUsersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpeakerProfileToUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
