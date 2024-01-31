import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdCardListComponent } from './ad-card-list.component';

describe('AdCardListComponent', () => {
  let component: AdCardListComponent;
  let fixture: ComponentFixture<AdCardListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdCardListComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AdCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
