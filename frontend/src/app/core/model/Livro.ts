import { Editora } from "./Editora"

export interface ItemBiblioteca {
  id: number
  titulo: string,
  descricao: string,
  generos: {id: number, nome: string}[],
  imagem_capa: string,
  quantidade: number,
  deletado: boolean,
  dataPublicacao: string,
}

export interface Livro extends ItemBiblioteca {
  isbn: string,
}

export interface Revista extends ItemBiblioteca {
  issn: string,
}