require 'sinatra'
require 'json'

get '/cursos' do
  File.readlines('cursos.json');
end

get '/bibliografia' do
  File.readlines('bibliografia.json');
end

get '/disciplinas' do
  File.readlines('disciplinas.json');
end

get '/livros' do
  File.readlines('livros.json');
end