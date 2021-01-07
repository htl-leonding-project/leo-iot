import { TestBed } from '@angular/core/testing';

import { TramService } from './tram.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';

describe('TramService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule],
    providers: [TramService]
  }));

  it('should be created', () => {
    const service: TramService = TestBed.get(TramService);
    expect(service).toBeTruthy();
  });
});
