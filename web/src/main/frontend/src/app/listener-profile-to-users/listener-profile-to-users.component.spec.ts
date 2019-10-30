import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListenerProfileToUsersComponent } from './listener-profile-to-users.component';

describe('ListenerProfileToUsersComponent', () => {
  let component: ListenerProfileToUsersComponent;
  let fixture: ComponentFixture<ListenerProfileToUsersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListenerProfileToUsersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListenerProfileToUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
