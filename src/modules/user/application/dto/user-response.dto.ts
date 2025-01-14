import { User } from '@user/domain/entities/user.entity';
export class UserResponse {
  id: number;
  email: string;
  name: string;
  createdAt: Date;
  updatedAt: Date;

  constructor(user: User) {
    this.id = user.id;
    this.email = user.email;
    this.name = user.name;
    this.createdAt = user.createdAt;
    this.updatedAt = user.updatedAt;
  }

  static of(user: User): UserResponse {
    return new UserResponse(user);
  }
}

export class UserDetailResponse {
  id: number;
  email: string;
  name: string;
  password: string;
  createdAt: Date;
  updatedAt: Date;

  constructor(user: User) {
    this.id = user.id;
    this.email = user.email;
    this.name = user.name;
    this.password = user.password;
    this.createdAt = user.createdAt;
    this.updatedAt = user.updatedAt;
  }

  static of(user: User): UserDetailResponse {
    return new UserDetailResponse(user);
  }
}
