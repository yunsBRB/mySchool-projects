export interface Pokemon {
  id: number;
  name: string;
  en: string;
  gen: number;
  types: string[];
  stats: number[];
  height: number;
  weight: number;
}

export interface NamedResource {
  name: string;
  url: string;
}

export interface Species {
  flavor_text_entries: { flavor_text: string; language: NamedResource }[];
  evolution_chain: { url: string };
}

export interface EvolutionNode {
  species: NamedResource;
  evolves_to: EvolutionNode[];
}

export interface EvolutionChain {
  chain: EvolutionNode;
}

export interface PokemonDetails {
  abilities: { ability: NamedResource; is_hidden: boolean }[];
}

export interface Filters {
  query: string;
  type: string;
  generation: string;
  sort: string;
  ids: readonly number[] | null;
}

export type CollectionView = 'all' | 'favorites' | 'team';
