interface RegisterForm {
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  phone: string | null | undefined;
  email: string | null | undefined;
}

interface RegisterResponse extends Omit<RegisterForm, 'password'> {
  id: number;
}

export { RegisterForm, RegisterResponse };
