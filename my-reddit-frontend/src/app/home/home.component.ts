import {Component, OnInit} from '@angular/core';
import {PostModel} from '../shared/post-model';
import {PostService} from '../shared/post.service';
import {faArrowDown, faArrowUp, faComments} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
