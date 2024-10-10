import requests
from bs4 import BeautifulSoup
url = 'http://localhost:8080/lab1_war_exploded/GraphMatrixServlet'

params = {
    'numVertices': 5,
    'numEdges': 4
}

response = requests.get(url, params=params)

if response.status_code == 200:
    soup = BeautifulSoup(response.text, 'html.parser')

    matrix_values = soup.find_all('td')

    matrix_size = int(params['numVertices'])
    matrix = []
    
    for i in range(0, len(matrix_values), matrix_size):
        row = [td.text for td in matrix_values[i:i+matrix_size]]
        matrix.append(row)
    
    print("Adjacency Matrix:")
    for row in matrix:
        print(" ".join(row))
else:
    print(f"Failed to get the matrix. Status code: {response.status_code}")
