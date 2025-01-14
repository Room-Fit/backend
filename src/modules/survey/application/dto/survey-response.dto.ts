import { SurveyAnswer } from '@survey/domain/schemas/survey-answer.schema';
import { Option } from '@survey/domain/types/survey.type';

export class SurveyAnswerResponse {
  _id: string;
  title: string;
  description?: string;
  questions: QuestionResponse[];
  constructor(_id, title, description, questions) {
    this._id = _id;
    this.title = title;
    this.description = description;
    this.questions = questions;
  }

  static of({ _id, title, description, questions }: SurveyAnswer) {
    return new SurveyAnswerResponse(_id, title, description, questions);
  }
}

export class QuestionResponse {
  questionId: number;
  questionText: string;
  questionType: string;
  dataType: string;
  options?: Option[];
}
