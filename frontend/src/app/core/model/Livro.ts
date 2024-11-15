import { Autor } from "./Autor"
import { Editora } from "./Editora"
import { Genero } from "./Genero"

export interface ItemBiblioteca {
  id: number
  titulo: string,
  descricao: string,
  generos: Genero[],
  imagem_capa: string,
  quantidade: number,
  deletado: boolean,
  dataPublicacao: string,
}

export interface Livro extends ItemBiblioteca {
  autor: Autor,
  isbn: string,
}

export interface Revista extends ItemBiblioteca {
  issn: string,
}