import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListenerProfileComponent } from './listener-profile.component';

describe('ListenerProfileComponent', () => {
  let component: ListenerProfileComponent;
  let fixture: ComponentFixture<ListenerProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListenerProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListenerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
