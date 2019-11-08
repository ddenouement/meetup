import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBagesComponent } from './admin-bages.component';

describe('AdminBagesComponent', () => {
  let component: AdminBagesComponent;
  let fixture: ComponentFixture<AdminBagesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminBagesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
