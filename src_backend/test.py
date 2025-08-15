from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI()

# In-memory database (for demonstration purposes)
items = []

# Pydantic model for item data
class Item(BaseModel):
    name: str
    description: str

@app.get("/")
def read_root():
    return {"Hello": "World"}

# Create an item
@app.post("/items/", response_model=Item)
async def create_item(item: Item):
    items.append(item)
    return item

@app.get("/items/{item_id}", response_model=Item)
async def read_item(item_id: int):
    if item_id < 0 or item_id >= len(items):
        raise HTTPException(status_code=404, detail="Item not found")
    return items[item_id]

# Update an item
@app.put("/items/{item_id}", response_model=Item)
async def update_item(item_id: int, item: Item):
    if item_id < 0 or item_id >= len(items):
        raise HTTPException(status_code=404, detail="Item couldn't be updated")
    
    items[item_id] = item
    return item

# Delete an item
@app.delete("/items/{item_id}", response_model=Item)
async def delete_item(item_id: int):
    if item_id < 0 or item_id >= len(items):
        raise HTTPException(status_code=404, detail="Item couldn't be deleted")
    
    deleted_item = items.pop(item_id)
    return deleted_item
