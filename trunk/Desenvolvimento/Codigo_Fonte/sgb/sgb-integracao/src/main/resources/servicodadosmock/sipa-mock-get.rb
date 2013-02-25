require 'sinatra'
require 'json'

get '/cursos/:name' do
  # matches "GET /cursos/engenhariadesoftware" (curso de Engenharia de Software)
  # or "GET /cursos/sistemasdeinformacao" (curso de Sistemas de Informação)
  # params[:name] is 'curso'
  if  params[:name] == "engenhariadesoftware"
    File.readlines('curso-engenharia.json');
  elsif params[:name] == "sistemasdeinformacao"
    File.readlines('curso-sistemas.json');
  elsif params[:name] == "cursos"
    File.readlines('cursos.json');
  end
end

get '/disciplinas/:name' do
  # matches "GET /disciplinas/introducaoaengenhariadesoftware" (disciplina Introdução
  # a Engenharia de Software)
  # or "GET /disciplinas/introducaoaprogramacao" (disciplina de Introdução a Programação)
  # params[:name] is 'disciplina'
  if  params[:name] == "introducaoaengenhariadesoftware"
    File.readlines('disciplina-introducao-engenharia.json');
  elsif params[:name] == "introducaoaprogramacao"
    File.readlines('disciplina-introducao-programacao.json');
  elsif params[:name] == "disciplinas"
    File.readlines('disciplinas.json');
  end
end

get '/livros/:name' do
  # matches "GET /livros/engenhariadesoftware" (livro Engenharia de Software)
  # or "GET /livros/javacomoprogramar" (livro Java Como Programar)
  # params[:name] is 'disciplina'
  if params[:name] == "engenhariadesoftware"
    File.readlines('livro-engenharia-de-software.json');
  elsif params[:name] == "javacomoprogramar"
    File.readlines('livro-java-como-programar.json');
  elsif params[:name] == "livros"
    File.readlines('livros.json');
  end
end