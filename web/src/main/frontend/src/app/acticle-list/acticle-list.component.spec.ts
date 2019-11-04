import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActicleListComponent } from './acticle-list.component';

describe('ActicleListComponent', () => {
  let component: ActicleListComponent;
  let fixture: ComponentFixture<ActicleListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActicleListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActicleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
