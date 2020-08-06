import {Component, OnInit, Input} from '@angular/core';
import {PostService} from '../post.service';
import {PostModel} from '../post-model';
import {faComments} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-post-tile',
  templateUrl: './post-tile.component.html',
  styleUrls: ['./post-tile.component.css']
})
export class PostTileComponent implements OnInit {

  @Input() posts: PostModel[];
  faComments = faComments;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  goToPost(id: number): void {
    this.router.navigateByUrl('/view-post/' + id);
  }

}
