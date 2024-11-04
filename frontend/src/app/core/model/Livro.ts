export interface Livro {
  titulo: string;
  autor: {
    nome: string;
    id: number;
  };
  dataPublicacao: string;
  editora: string;
  genero: string;
  quantidade: number;
}