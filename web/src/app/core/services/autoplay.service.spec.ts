import { TestBed } from '@angular/core/testing';

import { AutoplayService } from './autoplay.service';

describe('AutoplayService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [AutoplayService]
  }));

  it('should be created', () => {
    const service: AutoplayService = TestBed.get(AutoplayService);
    expect(service).toBeTruthy();
  });
});
