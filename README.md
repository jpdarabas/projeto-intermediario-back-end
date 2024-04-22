# Projeto intermediário de Back End: API de localização

### Funções:
<ui>
  <li> Encontrar cidade com base nas coordenadas</li>
  <li> Salvar as cidades encontradas</li>
  <li> Ver a distância entre cidades salvas</li>
</ui>

## Como criar uma key:
<ui>
  <li> Crie uma conta no <a href=https://www.geodatasource.com/web-service/location-search>GeoDataSource</a> e adiquira o Location Search Web Service de graça.</li>
  <li> Copie sua chave <a href=https://www.geodatasource.com/license>aqui</a>.</li>
  <li> Crie uma variável de ambiente local:</li>
</ui>

Windows powershell:
```
Set-Item -Path Env:api_key -Value "SUA_CHAVE_API"
```
MacOS ou Linux:
```
export MINHA_CHAVE_API=seu_valor_de_chave_api
```


## Rotas:

### GET: /cidade

#### Requisição:
<table>
  <tr>
    <th>Parâmetro</th>
    <th>Tipo</th>
    <th>Descrição</th>
  </tr>
  <tr>
    <td>latitude</td>
    <td>double</td>
    <td>Latitude do local</td>
  </tr>
  <tr>
    <td>longitude</td>
    <td>double</td>
    <td>Longitude do local</td>
  </tr>
</table>

#### Resposta:
<table>
  <tr>
    <th>Parâmetro</th>
    <th>Tipo</th>
    <th>Descrição</th>
  </tr>
  <tr>
    <td>name</td>
    <td>String</td>
    <td>Nome da cidade</td>
  </tr>
  <tr>
    <td>region</td>
    <td>String</td>
    <td>Região/estado/distrido da cidade</td>
  </tr>
  <tr>
    <td>country</td>
    <td>String</td>
    <td>Sigla do país da cidade</td>
  </tr>
   <tr>
    <td>coordinates</td>
    <td>Coordinate</td>
    <td>Objeto contendo latitude e longitude</td>
  </tr>
</table>

### GET: /listaCidades

#### Resposta: lista de cidades encontradas em requisições /cidade


### Post: /distancia

#### Requisição (sendo cidade1 e cidade2 cidades JÁ ENCONTRADAS POR /cidade, conferir nome em /listaCidades):
```json
{"cidade1": "nome-cidade1",
"cidade2": "nome-cidade2"}
```


#### Resposta:
Distancia em km
