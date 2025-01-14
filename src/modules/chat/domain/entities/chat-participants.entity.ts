import { Entity, JoinColumn, ManyToOne, PrimaryGeneratedColumn } from 'typeorm';
import { User } from '@user/domain/entities/user.entity';
import { Chatroom } from './chatroom.entity';

@Entity({ name: 'chat_participants' })
export class ChatParticipants {
  @PrimaryGeneratedColumn()
  id: number;

  @ManyToOne(() => User, (user) => user.chatParticipants)
  participant: User;

  @ManyToOne(() => Chatroom, (chatroom) => chatroom.chatParticipants)
  @JoinColumn({ name: 'chatroom_id' })
  chatroom: Chatroom;
}
