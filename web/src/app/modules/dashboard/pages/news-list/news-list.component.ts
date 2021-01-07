import { Component, OnInit } from '@angular/core';
import { News } from 'src/app/shared/models';
import { NewsService } from 'src/app/core/services/news.service';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.scss']
})
export class NewsListComponent implements OnInit {

  newsList: Array<News>  = [];

  constructor(private newsService: NewsService) { }

  ngOnInit() {
    this.newsService.getNewsList().subscribe(list => {
      this.newsList = list;
    });
  }
}
