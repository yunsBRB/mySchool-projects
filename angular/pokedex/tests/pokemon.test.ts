// @ts-ignore
import {test} from 'node:test';
// @ts-ignore
import assert from 'node:assert/strict';
import {filterPokemon, parseIds, toggleSelection, evolutionIds} from '../src/app/utils/pokemon.utils';
import type {Pokemon, Filters} from '../src/app/models/pokemon';

const catalog: Pokemon[] = [
  {id: 25, name: 'Pikachu', en: 'pikachu', gen: 1, types: ['electric'], stats: [35, 55], height: 0.4, weight: 6},
  {id: 133, name: 'Évoli', en: 'eevee', gen: 1, types: ['normal'], stats: [55, 55], height: 0.3, weight: 6.5},
  {id: 152, name: 'Germignon', en: 'chikorita', gen: 2, types: ['grass'], stats: [45, 49], height: 0.9, weight: 6.4}
];
const filters: Filters = {query: '', type: '', generation: '', sort: 'id', ids: null};
test('Recherche française, anglaise et numéro', () => {
  for (const query of ['évoli', 'EVOLI', 'eevee', '#133']) assert.equal(filterPokemon(catalog, {
    ...filters,
    query
  })[0].id, 133);
  assert.equal(filterPokemon(catalog, {...filters, query: '025'})[0].id, 25);
});
test('Combinaison des filtres et favoris vides', () => {
  assert.equal(filterPokemon(catalog, {...filters, generation: '2', type: 'grass'}).length, 1);
  assert.equal(filterPokemon(catalog, {...filters, generation: '1', type: 'grass'}).length, 0);
  assert.equal(filterPokemon(catalog, {...filters, ids: []}).length, 0);
});
test('Le tri ne modifie pas le catalogue', () => {
  assert.equal(filterPokemon(catalog, {...filters, sort: 'power'})[0].id, 133);
  assert.equal(catalog[0].id, 25);
});
test('Sauvegarde corrompue, doublons et limites', () => {
  assert.deepEqual(parseIds('{broken'), []);
  assert.deepEqual(parseIds('[25,25,"133",null,133,152]', 2), [25, 133]);
});
test('Une équipe complète refuse un septième mais permet un retrait', () => {
  const team = [1, 2, 3, 4, 5, 6];
  assert.deepEqual(toggleSelection(team, 7, 6), team);
  assert.deepEqual(toggleSelection(team, 3, 6), [1, 2, 4, 5, 6]);
  assert.equal(team.length, 6);
});
test('Le comparateur refuse un troisième Pokémon', () => {
  assert.deepEqual(toggleSelection([25, 133], 152, 2), [25, 133]);
});
test('Toutes les branches d’évolution sont conservées', () => {
  assert.deepEqual(evolutionIds({
    species: {name: 'eevee', url: 'https://pokeapi.co/api/v2/pokemon-species/133/'},
    evolves_to: [{
      species: {name: 'vaporeon', url: 'https://pokeapi.co/api/v2/pokemon-species/134/'},
      evolves_to: []
    }, {species: {name: 'jolteon', url: 'https://pokeapi.co/api/v2/pokemon-species/135/'}, evolves_to: []}]
  }), [133, 134, 135]);
});
