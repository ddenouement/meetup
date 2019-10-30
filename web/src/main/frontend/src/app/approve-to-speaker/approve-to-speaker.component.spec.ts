import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveToSpeakerComponent } from './approve-to-speaker.component';

describe('ApproveToSpeakerComponent', () => {
  let component: ApproveToSpeakerComponent;
  let fixture: ComponentFixture<ApproveToSpeakerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveToSpeakerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveToSpeakerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
