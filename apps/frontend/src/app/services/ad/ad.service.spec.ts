import { TestBed } from '@angular/core/testing';

import { AdService } from './ad.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AdService', () => {
  let service: AdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AdService],
    });
    service = TestBed.inject(AdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
