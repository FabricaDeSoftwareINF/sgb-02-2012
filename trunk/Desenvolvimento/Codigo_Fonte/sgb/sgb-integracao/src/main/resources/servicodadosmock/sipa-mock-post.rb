require 'sinatra'
require 'json'

post '/cursos' do
  File.readlines('cursos.json');
end

post '/bibliografia' do
  File.readlines('bibliografia.json');
end

post '/disciplinas' do
  File.readlines('disciplinas.json');
end

post '/livros' do
  File.readlines('livros.json');
end