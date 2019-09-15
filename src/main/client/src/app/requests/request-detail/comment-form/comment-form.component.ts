import {Component, Input, OnInit} from '@angular/core';
import {NgForm} from "@angular/forms";
import {CommentService} from "../comment.service";

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css']
})
export class CommentFormComponent implements OnInit {

  @Input() requestId: number;
  constructor(private commentService: CommentService) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const detail = form.value.detail;
    this.commentService.addComment(detail, this.requestId);
  }
}
