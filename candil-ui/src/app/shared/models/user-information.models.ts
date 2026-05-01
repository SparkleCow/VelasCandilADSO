export interface UserInformation {
  username: string;
  imageUrl: string;
  roles?: Array<string | { authority?: string }>;
}
