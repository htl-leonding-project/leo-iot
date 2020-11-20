import { TestBed } from '@angular/core/testing';

import { NewsService } from './news.service';
import { HttpClientModule } from '@angular/common/http';

describe('NewsService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule],
    providers: [NewsService]
  }));

  it('should be created', () => {
    const service: NewsService = TestBed.get(NewsService);
    expect(service).toBeTruthy();
  });
});
