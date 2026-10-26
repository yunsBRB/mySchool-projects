import type { Filters, Pokemon, EvolutionNode } from '../models/pokemon';
export const normalize = (text: string) => text.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLowerCase().trim();
export const artwork = (id: number, shiny = false) => `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${shiny ? 'shiny/' : ''}${id}.png`;
export const numberLabel = (id: number) => '#' + String(id).padStart(3, '0');
export const statTotal = (pokemon: Pokemon) => pokemon.stats.reduce((sum, stat) => sum + stat, 0);
export function filterPokemon(catalog: readonly Pokemon[], filters: Filters): Pokemon[] {
  const query = normalize(filters.query).replace(/^#/, '');
  return catalog.filter(p =>
    (filters.ids === null || filters.ids.includes(p.id)) &&
    (!filters.type || p.types.includes(filters.type)) &&
    (!filters.generation || p.gen === Number(filters.generation)) &&
    (!query || normalize(p.name).includes(query) || normalize(p.en).includes(query) || String(p.id) === query || String(p.id).padStart(3, '0') === query)
  ).sort((a, b) => filters.sort === 'name' ? a.name.localeCompare(b.name, 'fr') : filters.sort === 'power' ? statTotal(b) - statTotal(a) : a.id - b.id);
}
export function parseIds(raw: string | null, limit = Infinity): number[] {
  try {
    const parsed: unknown = JSON.parse(raw ?? '[]');
    return Array.isArray(parsed) ? [...new Set(parsed.filter((id): id is number => Number.isInteger(id) && id > 0))].slice(0, limit) : [];
  } catch { return []; }
}
export function toggleSelection(ids: readonly number[], id: number, limit = Infinity): number[] {
  if (ids.includes(id)) return ids.filter(value => value !== id);
  return ids.length < limit ? [...ids, id] : [...ids];
}
export function evolutionIds(node: EvolutionNode): number[] {
  return [Number(node.species.url.split('/').filter(Boolean).pop()), ...node.evolves_to.flatMap(evolutionIds)];
}
