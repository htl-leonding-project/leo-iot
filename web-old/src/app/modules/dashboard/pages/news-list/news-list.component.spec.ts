import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsListComponent } from './news-list.component';
import { NewsComponent } from '../news/news.component';
import { NewsService } from 'src/app/core/services/news.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';

describe('NewsListComponent', () => {
  let component: NewsListComponent;
  let fixture: ComponentFixture<NewsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsListComponent, NewsComponent ],
      imports: [HttpClientModule],
      providers: [NewsService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
